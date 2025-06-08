package br.com.etecia.iservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOLocalService {

    private DbHelper dbHelper;

    /**
     * Construtor recebe o Context e cria o DbHelper.
     * Assim conseguimos acessar o banco de dados.
     */

    public DAOLocalService(Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Método para inserir um novo SERVICO no banco de dados.
     * Recebe um objeto ObjServico como parâmetro.
     */

    public long inserirService(ObjCardServicoPp service) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos valores
        ContentValues values = new ContentValues();

        //Colocando os valores
        values.put(dbHelper.COLUMN_NOME_SERV, service.getTxtNomeServicoPp());
        values.put(dbHelper.COLUMN_VALOR_SERV, service.getTxtValorServicoPp());
        values.put(dbHelper.COLUMN_DESCRICAO_SERV, service.getTxtDetalhesServicoPp());
        values.put(dbHelper.COLUMN_IMG_SERV, service.getImgServicoPp());

        //chave estrangeira
        values.put(dbHelper.COLUMN_ID_LOJA_FK, service.getCodigoLoja());

        // Insere no banco e retorna o ID gerado.
        long id = db.insert(DbHelper.TABLE_SERVICO, null, values);

        // Fecha o banco após operação.
        db.close();

        return id;
    }


    /**
     * Método para listar todas os LOJAS do banco.
     * Retorna uma Lista de ObjLoja.
     */
    public List<ObjCardServicoPp> readService() {

        // Instanciando a lista que vai ser retornada
        List<ObjCardServicoPp> lista = new ArrayList<>();

        // Obtém o banco no modo de leitura.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define as colunas que queremos consultar.
        String[] colunas = {
                dbHelper.COLUMN_ID_SERV,
                dbHelper.COLUMN_NOME_SERV,
                dbHelper.COLUMN_DESCRICAO_SERV,
                dbHelper.COLUMN_VALOR_SERV,
                dbHelper.COLUMN_ID_LOJA_FK
        };

        // Faz a consulta no banco.
        Cursor cursor = db.query(
                DbHelper.TABLE_SERVICO, // tabela
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
                long id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_SERV));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_NOME_SERV));
                String descricao =  cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_DESCRICAO_SERV));
                double valor = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_VALOR_SERV));
                long idLoja = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_LOJA_FK));

                //Objeto Loja vazio
                ObjCardServicoPp service = new ObjCardServicoPp();

                // setando as informaçoes
                service.setCodigo(id);
                service.setTxtNomeServicoPp(nome);
                service.setTxtDetalhesServicoPp(descricao);
                service.setTxtValorServicoPp(valor);
                service.setCodigoLoja(idLoja);

                //Adicionando a lista
                lista.add(service);

            }while (cursor.moveToNext());
        }

        // Fecha o cursor e o banco.
        cursor.close();
        db.close();

        return lista;  // Retorna a lista

    }

}
