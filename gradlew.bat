package com.dan.restaurant.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dan.restaurant.MyAplication
import com.dan.restaurant.R
import com.dan.restaurant.databinding.FragmentPhotoBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PhotoFragment : Fragment()
{
    private lateinit var binding: FragmentPhotoBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        checkPermission()
        binding.btnTakePhoto.setOnClickListener {
            if (checkPermission())
            {
                takePicture()
            }
        }
        return binding.root
    }

    private fun takePicture()
    {
        val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(requireContext().packageManager))
    }

    private fun checkPermission(): Boolean
    {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            val permissions = arrayOf(Manifest.permission.CAMERA)
            ActivityCompat.requestPermissions(requireActivity(), permissions, 0)
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            {
                return true
            }
            return false
        } else
    