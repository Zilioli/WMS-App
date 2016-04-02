package com.app.wms.wmsmenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.wms.wmsVO.VOProduct;

import java.util.List;

/**
 * Created by Carlos on 29/12/2015.
 */
public class ListProductAdapter extends ArrayAdapter<VOProduct>   implements View.OnClickListener {

    private List<VOProduct> _lstProducts;
    private final Context context;
    private int index_posicao = -1;

    static class ViewHolder {
        public TextView txtProduto;
        public TextView txtBarCode;
        public TextView txtLocalizacao;
        public TextView txtQtde;
    }

    public ListProductAdapter(Context context, List<VOProduct> lstProducts) {
        super(context, R.layout.iten_list_product, lstProducts);
        _lstProducts = lstProducts;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.iten_list_product, parent, false);

        ((TextView)rowView.findViewById(R.id.lblCdBarra)).setText(_lstProducts.get(position).getBarCode());
        ((TextView)rowView.findViewById(R.id.lblNomeProduto)).setText(_lstProducts.get(position).getProduto());
        ((TextView)rowView.findViewById(R.id.lblPosicao)).setText(_lstProducts.get(position).getPosicao());
        ((TextView)rowView.findViewById(R.id.lblQtde)).setText(_lstProducts.get(position).getQtde());
        ((TextView)rowView.findViewById(R.id.lblPosicaoItem)).setText( Integer.toString(position));
        rowView.findViewById(R.id.btnExcluirItem).setOnClickListener(this);
        return rowView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return _lstProducts.size();
    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.btnExcluirItem) {
            AlertDialog diaBox = AskOption(2);
            diaBox.show();
        }
    }

    private AlertDialog AskOption(int index)
    {

        index_posicao = index;
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(super.getContext())
                //set message, title, and icon
                .setTitle("Apagar Produto")
                .setMessage("Deseja excluir o produto " + _lstProducts.get(2).getProduto() + "?")
                .setIcon(R.drawable.side_nav_bar)
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                       if (index_posicao > -1)
                        {
                            //super.remove(_lstProducts.get(index_posicao));
                            index_posicao = -1;
                        }
                        dialog.dismiss();
                    }
                })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }
}