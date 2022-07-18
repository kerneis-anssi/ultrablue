package com.example.ultrablue

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ultrablue.database.AppDatabase
import com.example.ultrablue.database.Device
import com.example.ultrablue.database.DeviceRepository
import kotlinx.coroutines.launch

/*
    Implements a View Model to access and update the database
 */
class DeviceViewModel(application: Application): AndroidViewModel(application) {

    val repo: DeviceRepository
    val allDevices: LiveData<List<Device>>

    init {
        val deviceDao = AppDatabase.getDatabase(application).deviceDao()
        repo = DeviceRepository(deviceDao)
        allDevices = repo.allDevices
    }

    fun insert(device: Device) = viewModelScope.launch {
        repo.insert(device)
    }

    fun delete(device: Device) = viewModelScope.launch {
        repo.delete(device)
    }
}
