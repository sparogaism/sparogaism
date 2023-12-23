package com.example.lensshare

import android.os.Parcel
import android.os.Parcelable

class ModelProcess (val idcamera: Int, val namacamera: String, val statuscamera: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idcamera)
        parcel.writeString(namacamera)
        parcel.writeString(statuscamera)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<modellistcamera> {
        override fun createFromParcel(parcel: Parcel): modellistcamera {
            return modellistcamera(parcel)
        }

        override fun newArray(size: Int): Array<modellistcamera?> {
            return arrayOfNulls(size)
        }
    }
}