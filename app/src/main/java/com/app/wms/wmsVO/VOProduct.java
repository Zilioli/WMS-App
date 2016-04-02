package com.app.wms.wmsVO;

import java.io.Serializable;

/**
 * Created by Carlos on 16/12/2015.
 */
public class VOProduct implements Serializable {

    private static final long serialVersionUID = -7060210544600464481L;

    private String _Produto;
    private String _BarCode;
    private String _Qtde;
    private String _Posicao;

    public String getProduto()
    {
        return _Produto;
    }

    public void setProduto(String nome)
    {
        _Produto = nome;
    }

    public String getBarCode()
    {
        return  _BarCode;
    }

    public  void setBarCode(String barCode)
    {
        _BarCode = barCode;
    }

    public String getQtde()
    {
        return  _Qtde;
    }

    public  void setQtde(String Qtde)
    {
        _Qtde  = Qtde;
    }

    public String getPosicao()
    {
        return _Posicao;
    }

    public  void setPosicao(String Posicao)
    {
        _Posicao  = Posicao;
    }
}
