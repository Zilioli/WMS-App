package com.app.wms.wmsUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.wms.wmsmenu.R;
import com.google.zxing.integration.android.IntentIntegrator;


/**
 * Created by Carlos on 21/12/2015.
 */
public class ZXingTestActivity extends Activity {

    private static final String TAG = ZXingTestActivity.class.getSimpleName();
    private static final String PACKAGE_NAME = ZXingTestActivity.class.getPackage().getName();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.add_product);

        findViewById(R.id.btnCodeBar).setOnClickListener(scanProduct);
    }

    private final Button.OnClickListener scanProduct = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            IntentIntegrator integrator = new IntentIntegrator(ZXingTestActivity.this);
            integrator.addExtra("SCAN_WIDTH", 800);
            integrator.addExtra("SCAN_HEIGHT", 200);
            integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
            integrator.addExtra("PROMPT_MESSAGE", "Custom prompt to scan a product");
            integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);

        }
    };

}
