package com.example.modern_city.WorkUploadPhoto

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.modern_city.R
import com.example.modern_city.UploadPhoto.UploadResponse
import kotlinx.android.synthetic.main.activity_main_uplaod_work_photo.*
import kotlinx.android.synthetic.main.activity_main_upload_craft_photo.*
import kotlinx.android.synthetic.main.activity_main_upload_craft_photo.button_craftUpload
import kotlinx.android.synthetic.main.activity_main_upload_craft_photo.layout_root
import kotlinx.android.synthetic.main.activity_main_upload_craft_photo.progress_bar
import kotlinx.android.synthetic.main.activity_main_upload_photo.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class MainUplaodWorkPhotoActivity : AppCompatActivity(), UploadRequestBody.UploadCallback {

    private var selectedImageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_uplaod_work_photo)
        image_viewCraftWork.setOnClickListener {
            openImageChooser()
        }

        button_craftWorkUpload.setOnClickListener {
            uploadImage()
        }
    }


    private fun uploadImage() {

        val sharedPreferences = this.getSharedPreferences("crafs_userinf", Context.MODE_PRIVATE)
        var token=  sharedPreferences.getString("token",null)

        if (selectedImageUri == null) {
            layout_root.snackbar("Select an Image First")
            return
        }

        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        progress_bar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        MyAPI().uploadImage(
            MultipartBody.Part.createFormData(
                "craftsman_slider",
                file.name,
                body
            ),
            RequestBody.create(MediaType.parse("multipart/form-data"), token)
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                layout_root.snackbar(t.message!!)
                progress_bar.progress = 0
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                progress_bar.progress = 100
                layout_root.snackbar(response.body()?.msg.toString())
            }
        })

    }

    override fun onProgressUpdate(percentage: Int) {

        progress_bar.progress=percentage
    }
    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    image_viewCraftWork.setImageURI(selectedImageUri)
                }
            }
        }
    }


    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
}