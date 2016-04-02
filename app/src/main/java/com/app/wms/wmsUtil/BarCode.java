package com.app.wms.wmsUtil;

import android.widget.Toast;

import com.app.wms.wmsVO.VOProduct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos on 16/12/2015.
 */
public class BarCode {

    private String _Path = "";
    private String FILE_NAME ="ProductList.dat";

    //for(final VOBarCode obj : lstBarCode)

    public void setPath(String Path)
    {
        _Path = Path;
    }

    public boolean SalvarProduto(VOProduct _product) {

        try
        {
            if(_Path == "")
                throw new IOException("Path do arquivo com Lista de Produtos não informado");
        }
         catch (IOException e) {
            e.printStackTrace();
        }


        // Instancia objeto File com o arquivo que deverÃ¡ ser criado na memoria do celular
        File f = new File(_Path, FILE_NAME);

        // Cria o diretÃ³rio se ele nÃ£o existir
        f.mkdirs();

        List<VOProduct> lstProducts;
        Toast toast;
        FileOutputStream outStream;
        ObjectOutputStream objectOutStream;

        try
        {
            // Cria o arquivo se ele nÃ£o existir
            f.createNewFile();

            lstProducts = ListarProdutos();
            lstProducts.add(_product);

            outStream = new FileOutputStream(f.getAbsolutePath() + FILE_NAME);
            objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(lstProducts);
            objectOutStream.close();

          return true;
        }
        catch (IOException e) {
            // Tratamento de erro
            e.printStackTrace();

            return false;
        }
        finally{
            // Finaliza objetoz
            lstProducts = null;
            toast = null;
            outStream = null;
            objectOutStream = null;
            f = null;
        }
    }

    public List<VOProduct> ListarProdutos() {

        List<VOProduct> lstProducts = new ArrayList();
        Toast toast;
        ObjectInputStream objectInStream;
        FileInputStream inStream;
        File f;

        try{

            if(_Path == "")
                throw new IOException("Path do arquivo com Lista de Produtos não informado");

            //this.getCacheDir().getAbsolutePath()
            f = new File(_Path,FILE_NAME);
            inStream = new FileInputStream(f.getAbsolutePath() + FILE_NAME);
            objectInStream = new ObjectInputStream(inStream);

            lstProducts = ((List<VOProduct>)objectInStream.readObject());
        }
        catch (IOException e) {
            // Tratamento de erro
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            // Finaliza objetos
            toast = null;
            f = null;
            inStream = null;
            objectInStream = null;
        }

        return lstProducts;
    }
}
