package com.app.wms.wmsmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.wms.wmsUtil.BarCode;
import com.app.wms.wmsVO.VOProduct;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * Created by Carlos on 16/12/2015.
 */
public class AddProductActivity  extends  Fragment implements OnClickListener {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Defines the xml file for the fragment
        view = inflater.inflate(R.layout.add_product, container, false);
        view.findViewById(R.id.btnCodeBar).setOnClickListener(this);
        view.findViewById(R.id.btnCancelar).setOnClickListener(this);
        view.findViewById(R.id.btnSalvar).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(final View v){

        if(v.getId()==R.id.btnCodeBar) {

            IntentIntegrator integrator = new IntentIntegrator(AddProductActivity.this.getActivity());
            integrator.addExtra("SCAN_WIDTH", 800);
            integrator.addExtra("SCAN_HEIGHT", 200);
            //integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
            integrator.addExtra("PROMPT_MESSAGE", "Código de barras do produto");
            integrator.initiateScan();
        }
        else if (v.getId() == R.id.btnCancelar)
        {
            ((TextView)view.findViewById(R.id.txtCdBarra)).setText("");
            ((TextView)view.findViewById(R.id.txtProduto)).setText("");
            ((TextView)view.findViewById(R.id.txtLocalizacao)).setText("");
            ((TextView)view.findViewById(R.id.txtQtde)).setText("");
        }
        else if (v.getId() == R.id.btnSalvar)
        {
            BarCode objBarCode = new BarCode();
            VOProduct objProduto = new VOProduct();

            TextView objTxtCdBarra =((TextView)view.findViewById(R.id.txtCdBarra));
            TextView objTxtProduto =((TextView)view.findViewById(R.id.txtProduto));
            TextView objTxtLocalizacao =((TextView)view.findViewById(R.id.txtLocalizacao));
            TextView objTxtQtde =((TextView)view.findViewById(R.id.txtQtde));

            if (objTxtCdBarra.getText().toString().trim().isEmpty() || objTxtProduto.getText().toString().trim().isEmpty() ||
                    objTxtLocalizacao.getText().toString().trim().isEmpty()  || objTxtQtde.getText().toString().trim().isEmpty() )
                Toast.makeText(getActivity(),
                        "Campo(s) obrigatório(s) não foram preenchido(s)!", Toast.LENGTH_LONG).show();
            else
            {
                objProduto.setBarCode(objTxtCdBarra.getText().toString());
                objProduto.setProduto(objTxtProduto.getText().toString());
                objProduto.setPosicao(objTxtLocalizacao.getText().toString());
                objProduto.setQtde(objTxtQtde.getText().toString());

                objBarCode.setPath(AddProductActivity.super.getActivity().getCacheDir().getAbsolutePath());
                if (objBarCode.SalvarProduto(objProduto) == true) {
                    Toast.makeText(getActivity(),
                            "Produto Salvo com Sucesso!", Toast.LENGTH_LONG).show();

                    objTxtCdBarra.setText("");
                    objTxtProduto.setText("");
                    objTxtLocalizacao.setText("");
                    objTxtQtde.setText("");
                }
                else
                    Toast.makeText(getActivity(),
                        "Problemas ao Salvar Produto!", Toast.LENGTH_LONG).show();

            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            ((TextView)view.findViewById(R.id.txtCdBarra)).setText(scanContent);
        }
        else{
            ((TextView)view.findViewById(R.id.txtCdBarra)).setText("");
            Toast toast = Toast.makeText(getActivity(),
                    "O código dde barra foi lido!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
