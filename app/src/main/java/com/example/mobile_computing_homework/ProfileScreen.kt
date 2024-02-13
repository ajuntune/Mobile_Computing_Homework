package com.example.mobile_computing_homework

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.mobile_computing_homework.datastore.StoreProfileData
import com.example.mobile_computing_homework.datastore.StoreUserImage
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileScreen(navController: NavController){

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val nameDataStore = StoreProfileData(context)

    val imageDataStore = StoreUserImage(context)

    val savedName = nameDataStore.getUsername.collectAsState(initial = "name")

    var username by remember {
        mutableStateOf(savedName.value!!)
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            selectedImageUri = it
        }
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Edit Profile", fontSize = 50.sp)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = username,
            onValueChange = {username = it}
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { photoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        ) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_input_add),
                    contentDescription = "add image"
                )
                Text(
                    text = "Change image",
                    fontSize = 30.sp
                )
            }
        }

        AsyncImage(
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(30.dp)),
            model = selectedImageUri,
            contentDescription = null,
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick =
        {
            scope.launch {
                nameDataStore.saveUsername(username)
                if (selectedImageUri == null)
                else
                imageDataStore.saveImageUri(copyImageToAppStorage(context, selectedImageUri!!).toString())
            }
            navController.navigate("Conversation screen"){
                popUpTo("Conversation screen"){inclusive = true}
            }
        }) {
            Text(text = "Save and Return", fontSize = 40.sp)
        }
    }
}

fun copyImageToAppStorage(context: Context, imageUri: Uri): File? {
    val input = context.contentResolver.openInputStream(imageUri)
    input?.use { inputStream ->
        val outputDir = context.filesDir // Directory to store the image file
        val outputFile = File(outputDir, "image.jpg") // Name the image file as per your requirement
        FileOutputStream(outputFile).use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        return outputFile
    }
    return null
}
