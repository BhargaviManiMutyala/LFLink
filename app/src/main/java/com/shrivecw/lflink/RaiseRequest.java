package com.shrivecw.lflink;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RaiseRequest extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "RaiseRequest"; // Tag for logging
    private ImageView selectedImage;
    private EditText productNameInput, productDetailsInput;
    private Uri imageUri; // To store the URI of the selected image
    private String registrationNumber; // Store registration number

    // Firestore instance
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_request);
        Log.d(TAG, "onCreate: Activity created");

        // Retrieve the registration number from the intent
        registrationNumber = getIntent().getStringExtra("registration_number");
        Log.d(TAG, "onCreate: Registration number received: " + registrationNumber);

        selectedImage = findViewById(R.id.selected_image);
        productNameInput = findViewById(R.id.product_name_input);
        productDetailsInput = findViewById(R.id.product_details_input);

        Button uploadImageButton = findViewById(R.id.upload_image_button);
        Button submitButton = findViewById(R.id.submit_button);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Open image picker when clicked
        uploadImageButton.setOnClickListener(v -> openImagePicker());

        // Handle submit button click
        submitButton.setOnClickListener(v -> {
            String productName = productNameInput.getText().toString();
            String productDetails = productDetailsInput.getText().toString();

            // Check for valid inputs
            if (!productName.isEmpty() && !productDetails.isEmpty() && imageUri != null) {
                Log.d(TAG, "onClick: Valid input, submitting request");

                // Prepare data to store in Firestore
                Map<String, Object> requestData = new HashMap<>();
                requestData.put("product_name", productName);
                requestData.put("product_details", productDetails);
                requestData.put("image_uri", imageUri.toString());

                // Store the data in Firestore under the document with the registration number
                db.collection("LostLinkDB").document(registrationNumber)
                        .update(requestData)
                        .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                        .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));

                // Optionally, you can navigate to another activity after submission
                Intent intent = new Intent(RaiseRequest.this, HomePage.class); // Update with the correct activity
                startActivity(intent);
                //Toast.makeText(RaiseRequest.this, "Raise Request Clicked", Toast.LENGTH_SHORT).show();

            } else {
                Log.w(TAG, "onClick: Invalid input, please fill all fields and select an image");
                // Optionally show a message if inputs are invalid
                // e.g., Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to open the gallery or allow the user to pick an image
    private void openImagePicker() {
        Log.d(TAG, "openImagePicker: Opening image picker");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData(); // Get the URI of the selected image
            selectedImage.setImageURI(imageUri); // Display the image in the ImageView
            Log.d(TAG, "onActivityResult: Image selected: " + imageUri.toString());
        } else {
            Log.w(TAG, "onActivityResult: Image selection failed or cancelled");
        }
    }
}

