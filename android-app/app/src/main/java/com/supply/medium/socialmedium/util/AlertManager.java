package com.supply.medium.socialmedium.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by vinod on 10/14/2016.
 */
public class AlertManager {
    public static void messageDialog(Context ctx, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(title);
        builder.setMessage("Please check internet is working on your device.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                System.exit(0);
            }
        });
        Dialog d = builder.create();
        d.show();
    }

    public static void validationDialog(Context ctx, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog d = builder.create();
        d.show();
    }

   /* public static void alertDialog(Context ctx, String mac_address, String gatewy,Long min_dis,Long max_dis) {
            final Dialog dialog = new Dialog(ctx);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog);
            dialog.setCancelable(true);

            TextView settings = (TextView) dialog.findViewById(R.id.mac_address);
            TextView gateway = (TextView) dialog.findViewById(R.id.gateway);
            TextView min_distance = (TextView) dialog.findViewById(R.id.min_distance);
            TextView max_distance = (TextView) dialog.findViewById(R.id.max_distance);
            ImageView close = (ImageView) dialog.findViewById(R.id.close);

            settings.setText("MAC Address: "+(mac_address));
            gateway.setText("Gateway: "+gatewy);
            min_distance.setText("Minimum Distance: "+""+min_dis);
            max_distance.setText("Maximum Distance: "+""+max_dis);

            close.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            dialog.show();
    }*/

}
