package util;

public class CreditCardIdPrettyPrintImpl implements CreditCardIdPrettyPrint{
    private CreditCardIdPrettyPrintImpl() {
    }

    private final static CreditCardIdPrettyPrintImpl INSTANCE = new CreditCardIdPrettyPrintImpl();
    public static CreditCardIdPrettyPrintImpl getInstance(){
        return INSTANCE;
    }
    @Override
    public String getIdForPrettyPrint(long id){
        String idForPrint = String.valueOf(id);
        return transformId(idForPrint.toCharArray());
    }

    private String transformId(char[] idForPrint){
        StringBuilder stringBuilder = new StringBuilder();
        int countBeforeDrop = 0;
        for (char el : idForPrint){
            if(countBeforeDrop == 4){
                stringBuilder.append(' ').append(el);
                countBeforeDrop = 0;
            }else {
                stringBuilder.append(el);
            }
            countBeforeDrop++;
        }
        return stringBuilder.toString();
    }
}
