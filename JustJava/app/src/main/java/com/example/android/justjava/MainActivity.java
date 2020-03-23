package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method called when button is clicked
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateToppingCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolateTopping = chocolateToppingCheckBox.isChecked();

        EditText nameText = (EditText) findViewById(R.id.name);
        String name = nameText.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolateTopping);

        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolateTopping);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order summary");
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        startActivity(emailIntent);

    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolateTopping) {
        int price = quantity*5;
        if(addWhippedCream == true)
            price += quantity;
        if(addChocolateTopping == true)
            price += quantity;

        return price;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolateTopping) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nQuantity: " + quantity + " coffees";
        if(addWhippedCream == true)
            priceMessage += "\nWhipped cream added";
        if(addChocolateTopping == true)
            priceMessage += "\nChocolate topping added";
        priceMessage += "\nTotal Amount: $" + price;
        priceMessage += "\n\nThank You!";

        return priceMessage;
    }

    // increment coffees
    public void increment(View view) {
        quantity += 1;
        display(quantity);
    }

    // decrement coffees
    public void decrement(View view) {
        quantity -= 1;
        display(quantity);
    }

    // method to display quantity
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
