package br.com.etecia.iservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOLocalLoja {

    private DbHelper dbHelper;

    /**
     * Construtor recebe o Context e cria o DbHelper.
     * Assim conseguimos acessar o banco de dados.
     */
    public DAOLocalLoja(Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Método para inserir um novo Loja no banco de dados.
     * Recebe um objeto ObjLoja como parâmetro.
     */
    public long inserirLoja(ObjCardLoja loja) {

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

        return id;
    }

    /**
     * Método para listar todas os LOJAS do banco.
     * Retorna uma Lista de ObjLoja.
     */
    public List<ObjCardLoja> readLojas() {

        // Instanciando a lista que vai ser retornada
        List<ObjCardLoja> lista = new ArrayList<>();

        // Obtém o banco no modo de leitura.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define as colunas que queremos consultar.
        String[] colunas = {
                dbHelper.COLUMN_ID_LOJA,
                dbHelper.COLUMN_NOME_LOJA,
                dbHelper.COLUMN_IMG_LOJA,
                dbHelper.COLUMN_CPF_CNPJ_LOJA,
                dbHelper.COLUMN_DESCRICAO_LOJA,
                dbHelper.COLUMN_TEM_END_LOJA,
                dbHelper.COLUMN_TEM_SERV_LOJA,
                dbHelper.COLUMN_ID_PERFIL_FK
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
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_LOJA));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_NOME_LOJA));
                String descricao =  cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_DESCRICAO_LOJA));
                int temEnd = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TEM_END_LOJA));
                int temServ = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TEM_SERV_LOJA));
                int codUsu = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_PERFIL_FK));
                String cpfCnpj = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CPF_CNPJ_LOJA));
                byte[] img = cursor.getBlob(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_IMG_LOJA));

                //Objeto Loja vazio
                ObjCardLoja loja = new ObjCardLoja();

                // setando as informaçoes
                loja.setCodigLoja(id);
                loja.setNomeLoja(nome);
                loja.setDescricao(descricao);
                loja.setTemEndereco(temEnd == 1 ? true:false);
                loja.setTemServicos(temServ == 1 ? true:false);
                loja.setCodUsuario(codUsu);
                loja.setCpfCnpj(cpfCnpj);
                loja.setImgLoja(img);

                //Adicionando a lista
                lista.add(loja);

            }while (cursor.moveToNext());
        }

        // Fecha o cursor e o banco.
        cursor.close();
        db.close();

        return lista;  // Retorna a lista

    }

    /**
     * Método para atualizar um LOJA no banco de dados.
     * Recebe um objeto ObjLoja com o ID e os novos dados.
     */

    public boolean atualizarLoja(ObjCardLoja loja) {

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

        // Condição de qual registro será atualizado.
        String condicao = DbHelper.COLUMN_ID_LOJA + " = ?";
        String[] condArgumentos = {String.valueOf(condicao)};

        // Executa o update.
        int linhasModificadas = db.update(DbHelper.TABLE_LOJA, values, condicao, condArgumentos);

        // Fecha o cursor e o banco.
        db.close();

        if ( linhasModificadas != -1 ) return true;

        return false;
    }

    /**
     * Método para deletar um LOJA do banco de dados.
     * Recebe o objet LOJA, pega o codigo para excluir.
     */
    public boolean deleteLoja(ObjCardLoja loja) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String qual = DbHelper.COLUMN_ID_LOJA + " = ?";
        String[] qualArgumentos = {String.valueOf(loja.getCodigLoja())};

        // Executa o delete.
        int linhasAfetadas = db.delete(DbHelper.TABLE_LOJA, qual, qualArgumentos);

        // Fecha o banco após operação.
        db.close();

        //Retora true caso a exclusão tenha sido um sucesso
        if ( linhasAfetadas > 0 ) return true;

        return false;
    }





}
