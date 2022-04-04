package uz.example.chat.repository

import uz.example.chat.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface MainRepository {

    fun setUserNumber(string: String)
    // set user number to cache

    fun getUserNumber(): String
    // get user number

    fun setUserName(userName: String)
    // set user number to cache

    fun getUserName(): String
    // get user name

    fun setUserId(userId: Long)
    // set user id to cache

    fun getUserId(): Long
    // get user id

    suspend fun sendUser(user: User): Response<Boolean>
    // send user name number to server

    suspend fun insertAppContacts(list: List<User>)
    // we read all contacts on the device and send to server because we should know contacts users
    // is used my chatApp.And we will receive myApp users

    suspend fun insertContact(user: User)
    // add contact and save to device

    suspend fun getAppContacts(): Flow<List<User>>
    // get all myApp users

    suspend fun sendAllContact(contacts: ArrayList<User>): Response<List<User>>
    // send all contacts after read device to sort

//    suspend fun saveDeviceContacts(list: List<User>)
//    // save all device contacts
//
//    suspend fun getDeviceContacts(): Flow<List<User>>
//    // get all device contacts

}