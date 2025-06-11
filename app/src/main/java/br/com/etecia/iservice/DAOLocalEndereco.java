package br.com.etecia.iservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOLocalEndereco {

    private DbHelper dbHelper;

    /**
     * Construtor recebe o Context e cria o DbHelper.
     * Assim conseguimos acessar o banco de dados.
     */
    public DAOLocalEndereco(Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Método para inserir um novo Endereço no banco de dados.
     * Recebe um objeto ObjEndereco como parâmetro.
     */
    public long inserirEndereco(ObjEndereco endereco) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos valores
        ContentValues values = new ContentValues();

        //Colocando os valores
        values.put(dbHelper.COLUMN_CEP_ENDERECO, endereco.getCepCnpj());
        values.put(dbHelper.COLUMN_ESTADO_ENDERECO, endereco.getEstado());
        values.put(dbHelper.COLUMN_CIDADE_ENDERECO, endereco.getCidade());
        values.put(dbHelper.COLUMN_BAIRRO_ENDERECO, endereco.getBairro());
        values.put(dbHelper.COLUMN_RUA_ENDERECO, endereco.getRua());
        values.put(dbHelper.COLUMN_NUMERO_ENDERECO, endereco.getNumero());
        values.put(dbHelper.COLUMN_COMPLEMENTO_ENDERECO, endereco.getComplemento());

        //chave estrangeira
        values.put(dbHelper.COLUMN_ID_LOJA_END_FK, endereco.getCodigoLoja());

        // Insere no banco e retorna o ID gerado.
        long id = db.insert(DbHelper.TABLE_ENDERECO, null, values);

        // Fecha o banco após operação.
        db.close();

        return id;
    }


    /**
     * Método para listar todas os ENDEREÇOS do banco.
     * Retorna uma Lista de ObjEndereco.
     */
    public List<ObjEndereco> readEndereco() {

        List<ObjEndereco> lista = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {
                dbHelper.COLUMN_ID_ENDERECO,
                dbHelper.COLUMN_CEP_ENDERECO,
                dbHelper.COLUMN_ESTADO_ENDERECO,
                dbHelper.COLUMN_CIDADE_ENDERECO,
                dbHelper.COLUMN_BAIRRO_ENDERECO,
                dbHelper.COLUMN_RUA_ENDERECO,
                dbHelper.COLUMN_NUMERO_ENDERECO,
                dbHelper.COLUMN_COMPLEMENTO_ENDERECO,
                dbHelper.COLUMN_ID_LOJA_END_FK
        };

        Cursor cursor = db.query(
                DbHelper.TABLE_ENDERECO,
                colunas,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                long idEndereco = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_ENDERECO));
                int cep = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CEP_ENDERECO));
                String estado = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ESTADO_ENDERECO));
                String cidade = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CIDADE_ENDERECO));
                String bairro = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BAIRRO_ENDERECO));
                String rua = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_RUA_ENDERECO));
                int numero = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_NUMERO_ENDERECO));
                String complemento = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_COMPLEMENTO_ENDERECO));
                long idLoja = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_LOJA_END_FK));

                ObjEndereco endereco = new ObjEndereco();
                endereco.setCodigo(idEndereco);
                endereco.setCepCnpj(cep);
                endereco.setEstado(estado);
                endereco.setCidade(cidade);
                endereco.setBairro(bairro);
                endereco.setRua(rua);
                endereco.setNumero(numero);
                endereco.setComplemento(complemento);
                endereco.setCodigoLoja(idLoja);

                lista.add(endereco);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lista;
    }





}
