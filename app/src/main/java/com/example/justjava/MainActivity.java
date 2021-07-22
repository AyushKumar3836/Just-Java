package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // price of chocolate=7,whipped cream=5 and price of 1 cup of coffee=10

    // this method is called when the order button is clicked
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream =whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox=(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate=chocolateCheckBox.isChecked();
        EditText text=(EditText) findViewById(R.id.name_field);
        String name=text.getText().toString();
        int price = calculatePrice(quantity,10,hasWhippedCream,hasChocolate);
String priceMessage=createOrderSummary(price,hasWhippedCream,hasChocolate,name);
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }


    }
    private int  calculatePrice(int quantity,int price,boolean hasWhippedCream,boolean hasChocolate)
    {
        if(hasWhippedCream && hasChocolate)
        return quantity*(price+5+7);
        else if(hasChocolate)
            return quantity*(price+7);
        else if( hasWhippedCream)
            return quantity*(price+5);
        return quantity*price;

    }
    private String createOrderSummary(int price,boolean hasWhippedCream,boolean hasChocolate,String name)
    {
        String priceMessage=getString(R.string.order_summary_name,name)+"\n";
        priceMessage=priceMessage+getString(R.string.add_whipped_cream,hasWhippedCream)+"\n";
        priceMessage=priceMessage+getString(R.string.add_chocolate,hasChocolate)+"\n";
        priceMessage=priceMessage+getString(R.string.quantity,quantity)+"\n";
        priceMessage=priceMessage+getString(R.string.total,price);
        priceMessage=priceMessage+"\n "+getString(R.string.thank_you);
        return priceMessage;

    }



    //This method is called when the plush button is clicked
    public void increment(View view) {

        quantity = quantity + 1;
        display(quantity);

    }

    // This method is called when the minus button is clicked
    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity < 0) {
            Toast.makeText(this,"you cannot have less than 0 cup of coffee",Toast.LENGTH_SHORT).show();
            quantity = 0;
            return;
        }

        display(quantity);

    }

    //This method displays the given quantity value on the screen
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}





