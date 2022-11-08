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
    private Chip callButton;
    private Chip textButton;
    private Chip emailButton;
    private Chip mapButton;
    private Chip websiteButton;
    private ContactInfoBinding mBinding;

    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ContactInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.ENTRY_KEY);
        callButton = findViewById(R.id.call_chip);
        textButton = findViewById(R.id.text_chip);
        emailButton = findViewById(R.id.email_chip);
        mapButton = findViewById(R.id.location_chip);
        websiteButton = findViewById(R.id.web_chip);
        TextView name = findViewById(R.id.text_chip);
        final TextView phoneNum = findViewById(R.id.textPhone);
        final TextView emailAdd = findViewById(R.id.textEmailAddress);
        final TextView address = findViewById(R.id.textAddress);
        final TextView website = findViewById(R.id.textWebsite);
        name.setText(c.getFullName());
        phoneNum.setText(c.getPhoneNumber());
        emailAdd.setText(c.getEmailAddress());
        address.setText(c.getAddress());
        website.setText(c.getWebsite());

        this.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallClick(phoneNum);
            }
        });
        this.textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTextClick(phoneNum);
            }
        });
        this.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmailClick(emailAdd);
            }
        });
        this.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMapClick(address);
            }
        });
        this.websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWebsiteClick(website);
            }
        });
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
