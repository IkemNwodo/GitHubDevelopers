package engineers.software.ng.developers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Developers implements Parcelable{

    @SerializedName("username")
    private String username;

    @SerializedName("state")
    private String state;

    @SerializedName("avatar_url")
    private String avatar_url;


    protected Developers(Parcel in) {
        username = in.readString();
        state = in.readString();
        avatar_url = in.readString();
    }

    public static final Creator<Developers> CREATOR = new Creator<Developers>() {
        @Override
        public Developers createFromParcel(Parcel in) {
            return new Developers(in);
        }

        @Override
        public Developers[] newArray(int size) {
            return new Developers[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(state);
        dest.writeString(avatar_url);
    }
}
