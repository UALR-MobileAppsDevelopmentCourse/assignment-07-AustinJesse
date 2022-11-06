package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import edu.ualr.intentsassignment.databinding.ContactInfoBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    private ContactInfoBinding mBinding;

    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ContactInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.ENTRY_KEY);
        mBinding.contactName.setText(c.getFullName());
        mBinding.textPhone.setText(c.getPhoneNumber());
        mBinding.textEmailAddress.setText(c.getEmailAddress());
        mBinding.textAddress.setText(c.getAddress());
        mBinding.textWebsite.setText(c.getWebsite());

    }

    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step

    public void onCallClick(View view) {
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.ENTRY_KEY);
        String phoneNumberUri = "tel:" + c.getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
        startActivity(intent);
    }

    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step

    public void onTextClick(View view) {
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.ENTRY_KEY);
        String smsUri = "smsto:" + c.getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
        intent.putExtra("message", "Hello!");
        startActivity(intent);
    }
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step

    public void onMapClick(View view) {
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.ENTRY_KEY);
        String place = c.getAddress();
        String placeUri = String.format("geo:0,0?q=(%s)", place);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(intent);
    }

    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step

    public void onEmailClick(View view) {
        String emailSubject = "Hello";
        String emailText = "Hello!";
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.ENTRY_KEY);
        String emailReceiver = c.getEmailAddress();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        startActivity(Intent.createChooser(intent,"To complete action choose:"));
    }

    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step

    public void onWebsiteClick(View view) {
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.ENTRY_KEY);
        String webUri = "https://" + c.getWebsite();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
        startActivity(intent);
    }

}
