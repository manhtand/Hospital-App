package com.example.krankenhaus.ui.service.labor.ui.main;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.krankenhaus.databinding.FragmentLaborResultMriBinding;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.relations.MRIAndRecord;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LaborResultMRIFragment extends Fragment {
    private LaborViewModel laborViewModel;
    private MRIAndRecord mriAndRecord;
    private FragmentLaborResultMriBinding binding;
    private Uri selectedImageUri;
    int SELECT_PICTURE = 200;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        laborViewModel = LaborActivity.obtainViewModel(getActivity());

        laborViewModel.getMriAndRecord().observe(getActivity(), new Observer<MRIAndRecord>() {
            @Override
            public void onChanged(MRIAndRecord input) {
                mriAndRecord = input;
                setCurrentImage();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLaborResultMriBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("MRI Result");

        binding.laborResultMriInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        binding.laborResultMriSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });
        return root;
    }

    private void setCurrentImage(){
        if(mriAndRecord.mri.getImage()==null){
            return;
        }
        byte[] blob= mriAndRecord.mri.getImage();
        Bitmap bmp= BitmapFactory.decodeByteArray(blob,0,blob.length);
        binding.imageView3.setImageBitmap(bmp);
    }

    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout

                    binding.imageView3.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private void saveImage(){
        byte[] inputData = null;
        try {
            InputStream iStream = this.getContext().getContentResolver().openInputStream(selectedImageUri);
            inputData = getBytes(iStream);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException i){
            i.printStackTrace();
        }
        laborViewModel.updateMRI(new MRI(mriAndRecord.record.getRecordId(), inputData));
    }
    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}