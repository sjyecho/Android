package com.example.kaohedemo_4_1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class DialFragment extends Fragment implements View.OnClickListener ,IButton{

    IButton iButton;
    TextView numberView;
    String phoneNumbers="18656968776";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contact = inflater.inflate(R.layout.dial_fragment, container, false);
        Button one = contact.findViewById(R.id.one);
        one.setOnClickListener(this);
        Button two = contact.findViewById(R.id.two);
        two.setOnClickListener(this);
        Button three = contact.findViewById(R.id.three);
        three.setOnClickListener(this);
        Button four = contact.findViewById(R.id.four);
        four.setOnClickListener(this);
        Button five = contact.findViewById(R.id.five);
        five.setOnClickListener(this);
        Button six = contact.findViewById(R.id.six);
        six.setOnClickListener(this);
        Button seven = contact.findViewById(R.id.seven);
        seven.setOnClickListener(this);
        Button eight = contact.findViewById(R.id.eight);
        eight.setOnClickListener(this);
        Button nine = contact.findViewById(R.id.nine);
        nine.setOnClickListener(this);
        Button zero = contact.findViewById(R.id.zero);
        zero.setOnClickListener(this);
        Button del = contact.findViewById(R.id.del);
        del.setOnClickListener(this);
        Button dial = contact.findViewById(R.id.dial);
        dial.setOnClickListener(this);
        Button del_one = contact.findViewById(R.id.del_one);
        del_one.setOnClickListener(this);

        numberView = contact.findViewById(R.id.numberView);
        return contact;
    }

    @SuppressWarnings("all")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.one:
                phoneNumbers=phoneNumbers+"1";
                numberView.setText(phoneNumbers);
                break;
            case R.id.two:
                phoneNumbers=phoneNumbers+"2";
                numberView.setText(phoneNumbers);
                break;
            case R.id.three:
                phoneNumbers=phoneNumbers+"3";
                numberView.setText(phoneNumbers);
                break;
            case R.id.four:
                phoneNumbers=phoneNumbers+"4";
                numberView.setText(phoneNumbers);
                break;
            case R.id.five:
                phoneNumbers=phoneNumbers+"5";
                numberView.setText(phoneNumbers);
                break;
            case R.id.six:
                phoneNumbers=phoneNumbers+"6";
                numberView.setText(phoneNumbers);
                break;
            case R.id.seven:
                phoneNumbers=phoneNumbers+"7";
                numberView.setText(phoneNumbers);
                break;
            case R.id.eight:
                phoneNumbers=phoneNumbers+"8";
                numberView.setText(phoneNumbers);
                break;
            case R.id.nine:
                phoneNumbers=phoneNumbers+"9";
                numberView.setText(phoneNumbers);
                break;
            case R.id.zero:
                phoneNumbers=phoneNumbers+"0";
                numberView.setText(phoneNumbers);
                break;
            case R.id.del:
                phoneNumbers="";
                numberView.setText("");
                break;
            case R.id.dial:
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                    iButton.setOnBtClick("123");
                }
                break;
            case R.id.del_one:
                int length=phoneNumbers.length();
                try {
                    phoneNumbers=phoneNumbers.substring(0,length-1);
                } catch (Exception e) {
                    //substring会出现空指针
                }
                numberView.setText(phoneNumbers);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(getContext(), "您未进行授权,请授权后再进行拨号", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }

    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phoneNumbers));
        startActivity(intent);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iButton=(IButton) context;
    }

    /**
     * 实现IButton中的方法
     * @param text
     */
    @Override
    public void setOnBtClick(String text) {

    }

    /**
     * 使用接口回调向通话记录传值
     */
    public interface OnTrans{

    }

    private OnTrans trans;

    /**
     * 对外提供访问的方法
     * @param trans
     */
    public void setOnTransValues(OnTrans trans){
        this.trans=trans;
    }
}
