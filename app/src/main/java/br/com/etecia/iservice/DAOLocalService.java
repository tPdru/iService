package br.com.etecia.iservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    /*public boolean inserirPerfil(ObjCardLoja loja) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos valores
        ContentValues values = new ContentValues();

        //Colocando os valores
        values.put(dbHelper.COLUMN_NOME_LOJA, loja.getNomeLoja());
        values.put(dbHelper.COLUMN_IMG_LOJA, loja.getImgLoja());
        values.put(dbHelper.COLUMN_CPF_CNPJ_LOJA, loja.getCpfCnpj());
        values.put(dbHelper.COLUMN_DESCRICAO_LOJA, loja.getDescricao());
        values.put(dbHelper.COLUMN_TEM_END_LOJA, loja.isTemEndereco());
        values.put(dbHelper.COLUMN_TEM_SERV_LOJA, loja.isTemServicos());

        //chave estrangeira
        values.put(dbHelper.COLUMN_ID_PERFIL_FK, loja.getCodUsuario());

        // Insere no banco e retorna o ID gerado.
        long id = db.insert(DbHelper.TABLE_LOJA, null, values);

        // Fecha o banco após operação.
        db.close();

        // True em caso de sucesso
        if ( id != -1 ) return true;

        return false;
    }*/


}
