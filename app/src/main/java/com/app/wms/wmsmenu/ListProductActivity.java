package com.app.wms.wmsmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.wms.wmsUtil.BarCode;
import com.app.wms.wmsVO.VOProduct;

import java.util.List;

/**
 * Created by Carlos on 16/12/2015.
 */
public class ListProductActivity  extends Fragment {

    ListView listView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_product, container, false);

        BarCode objBarCode = new BarCode();
        objBarCode.setPath(ListProductActivity.super.getActivity().getCacheDir().getAbsolutePath());
        List<VOProduct> lstProduct =  objBarCode.ListarProdutos();
        listView = (ListView)view.findViewById(R.id.listViewProduct);
        ListProductAdapter objAdapter = new ListProductAdapter(super.getContext(), lstProduct);
        listView.setAdapter(objAdapter);

        return view;

    }
}
