package com.omegastore.amazonclone.MenuFiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.omegastore.amazonclone.HomeActivity;
import com.omegastore.amazonclone.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddProduct extends BaseActivity {

    ImageView addProdImg, addProductBack;
    EditText addProdName, addProdPrice, addProdDesc, addProdCategory;
    TextView confirmAdd;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri setImageUri;

    String finalImageUri;
    public static String passName;
    Toolbar addtoolbar;

    LinearLayout dynamicContent;
    LinearLayout bottonNavBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        bottonNavBar = (LinearLayout) findViewById(R.id.bottomNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_add_product, null);
        dynamicContent.addView(wizard);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.bottom_addprod);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F51B5"));


        addProdImg = findViewById(R.id.addProductImg);
        addProdName = findViewById(R.id.addProductName);
        addProdPrice = findViewById(R.id.addProductPrice);
        addProdDesc = findViewById(R.id.addProductDesc);
        addProdCategory = findViewById(R.id.addProductCategory);
        confirmAdd = findViewById(R.id.confirmAddProd);
        addProductBack = findViewById(R.id.addProductBack);
        addtoolbar = findViewById(R.id.addtoolbar);

        addtoolbar.setBackgroundResource(R.drawable.bg_color);

        storage = FirebaseStorage.getInstance();

        addProdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open gallery to select image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        confirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate input fields
                if (validateFields()) {
                    // Check if an image is selected
                    if (setImageUri != null) {
                        // Proceed with adding the product to the database
                        storageReference = storage.getReference().child("products").child(addProdName.getText().toString());
                        addingToProdList();
                    } else {
                        Toast.makeText(AddProduct.this, "Please choose a product image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

        private boolean validateFields() {
            String name = addProdName.getText().toString();
            String price = addProdPrice.getText().toString();
            String desc = addProdDesc.getText().toString();
            String category = addProdCategory.getText().toString();

            if (TextUtils.isEmpty(name)) {
                addProdName.setError("Please enter name of the product");
                return false;
            } else if (TextUtils.isEmpty(price)) {
                addProdPrice.setError("Please enter price of the product");
                return false;
            } else if (TextUtils.isEmpty(desc)) {
                addProdDesc.setError("Please enter description of the product");
                return false;
            } else if (TextUtils.isEmpty(category)) {
                addProdCategory.setError("Please enter category of the product");
                return false;
            }
            return true;
        }

        private void addingToProdList() {
            String saveCurrentDate, saveCurrentTime;

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
            saveCurrentDate = currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calendar.getTime());

            DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All");

            final HashMap<String, Object> prodMap = new HashMap<>();
            prodMap.put("pid", addProdName.getText().toString());
            prodMap.put("name", addProdName.getText().toString());
            prodMap.put("price", "₹" + addProdPrice.getText().toString());
            prodMap.put("category", addProdCategory.getText().toString());
            prodMap.put("description", addProdDesc.getText().toString());
            prodMap.put("date", saveCurrentDate);
            prodMap.put("time", saveCurrentTime);

            if (setImageUri != null) {
                storageReference.putFile(setImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    finalImageUri = uri.toString();
                                    Log.i("AddingToProdList", "Image added successfully to storage");
                                    prodMap.put("img", finalImageUri);

                                    prodListRef.child("User View").child("Products")
                                            .child(addProdName.getText().toString()).updateChildren(prodMap)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        passName = addProdName.getText().toString();
                                                        Toast.makeText(AddProduct.this, "Product added successfully after verification", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(AddProduct.this, HomeActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Log.e("AddingToProdList", "Failed to add product to database: " + task.getException().getMessage());
                                                        Toast.makeText(AddProduct.this, "Failed to add product. Please try again.", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            });
                        } else {
                            Log.e("AddingToProdList", "Failed to upload image to storage: " + task.getException().getMessage());
                            Toast.makeText(AddProduct.this, "Failed to upload image. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            setImageUri = data.getData();

            if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), setImageUri);
                    addProdImg.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

