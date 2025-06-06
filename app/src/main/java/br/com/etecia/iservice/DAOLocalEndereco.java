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

        // Instanciando a lista que vai ser retornada
        List<ObjEndereco> lista = new ArrayList<>();

        // Obtém o banco no modo de leitura.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define as colunas que queremos consultar.
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

        // Faz a consulta no banco.
        Cursor cursor = db.query(
                DbHelper.TABLE_LOJA, // tabela
                colunas,                 // colunas
                null,                    // where
                null,                    // whereArgs
                null,                    // groupBy
                null,                    // having
                null                     // orderBy
        );

        // Percorre o Cursor e cria objetos ObjServico.
        if (cursor.moveToFirst()) {

            do {
                long id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_ENDERECO));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CEP_ENDERECO));
                String descricao =  cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ESTADO_ENDERECO));
                int temEnd = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CIDADE_ENDERECO));
                int temServ = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_BAIRRO_ENDERECO));
                int codUsu = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_RUA_ENDERECO));
                String cpfCnpj = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_NUMERO_ENDERECO));
                String img = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_COMPLEMENTO_ENDERECO));
                long idLoja = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_LOJA_END_FK));

                //Objeto Loja vazio
                ObjEndereco endereco = new ObjEndereco();

                // setando as informaçoes
                //endereco.setCodigLoja(id);
                //endereco.setNomeLoja(nome);
                //endereco.setDescricao(descricao);
                //endereco.setTemEndereco(temEnd == 1 ? true:false);
                //endereco.setTemServicos(temServ == 1 ? true:false);
                //endereco.setCodUsuario(codUsu);
                //endereco.setCpfCnpj(cpfCnpj);

                //Adicionando a lista
                //lista.add(loja);

            }while (cursor.moveToNext());
        }

        // Fecha o cursor e o banco.
        cursor.close();
        db.close();

        return lista;  // Retorna a lista

    }





}
