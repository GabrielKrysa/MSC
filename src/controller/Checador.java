package controller;

/**
 * classe responsavel por checar informações antes do cadastro.
 *
 * @author Krysa
 */
public class Checador {

    /**
     * Método responsavel por checar se o cpf informado pelo usuário é valido.
     *
     * @param cpf String contendo o cpf do usuário.
     * @return true se for valido, false se não for valido.
     */
    public boolean checkCPF(String cpf) {
        String strCpf = "";

        for (int y = 0; y < cpf.length(); y++) {

            char c = cpf.charAt(y);
            if (!(c == ' ' || c == '.' || c == '-')) {
                strCpf += "" + c;
            }
        }

        if (strCpf.equals("")) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
            digitoCPF = Integer.parseInt(strCpf.substring(nCount - 1, nCount));

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.  
            d1 = d1 + (11 - nCount) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.  
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        //Primeiro resto da divisão por 11.  
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.  
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        //Digito verificador do CPF que está sendo validado.  
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.  
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.  
        return nDigVerific.equals(nDigResult);
    }

    private int getDia(String data) {
        String dia = "";
        int i = 0;
        while (true) {
            if (data.charAt(i) != '-') {
                dia += "" + data.charAt(i);
            } else {
                break;
            }
            i++;
        }
        int diaCerto = Integer.parseInt(dia);
        return diaCerto;
    }

    private int getMes(String data) {
        String mes = "";
        int i = 0;
        do {
            i++;
        } while (data.charAt(i) != '-');

        mes += "" + data.charAt(i + 1);
        if (data.charAt(i + 2) != '-') {
            mes += "" + data.charAt(i + 2);
        }

        int mesCerto = Integer.parseInt(mes);
        return mesCerto;
    }

    private int getAno(String data) {
        int i = data.length() - 4;
        String ano = "" + data.charAt(i);
        ano += "" + data.charAt(i + 1);
        ano += "" + data.charAt(i + 2);
        ano += "" + data.charAt(i + 3);

        int anoCerto = Integer.parseInt(ano);
        return anoCerto;
    }

    public boolean checkData(String dataInicio1, String dataFim1, String dataInicio2, String dataFim2) {

        int inicio1 = getDia(dataInicio1), inicio2 = getDia(dataInicio2), fim1 = getDia(dataFim1), fim2 = getDia(dataFim2), mesIni1 = getMes(dataInicio1), mesFim1 = getMes(dataFim1), mesIni2 = getMes(dataInicio2), mesFim2 = getMes(dataFim2), ano1 = getAno(dataInicio1), ano2 = getAno(dataInicio2);

        if ((inicio2 == inicio1 && fim2 == fim1) || (inicio2 < fim1 && fim2 > fim1) || (inicio2 < inicio1 && fim2 > inicio1) || (inicio2 < fim1 && fim2 > inicio1) || (inicio2 < inicio1 && fim2 > fim1)) {
            if ((mesIni1 == mesIni2 && mesFim1 == mesFim2) || (mesIni2 < mesFim1 && mesIni2 > mesIni1) || (mesIni2 < mesIni1 && mesFim2 > mesIni1) || (mesIni2 < mesFim1 && mesFim2 > mesIni1) || (mesIni2 < mesIni1 && mesFim2 > mesFim1)) {
                if (ano1 == ano2) {
                   return false;
                }
            }
        } else if ((inicio2 == inicio1 && fim2 == fim1) || (inicio2 < fim1 && fim2 > fim1) || (inicio2 < inicio1 && fim2 > inicio1) || (inicio2 < fim1 && fim2 > inicio1) || (inicio2 < inicio1 && fim2 > fim1)) {
            if ((mesIni1 == mesIni2 && mesFim1 == mesFim2) || (mesIni2 < mesFim1 && mesIni2 > mesIni1) || (mesIni2 < mesIni1 && mesFim2 > mesIni1) || (mesIni2 < mesFim1 && mesFim2 > mesIni1) || (mesIni2 < mesIni1 && mesFim2 > mesFim1)) {
                if (ano1 == ano2) {
                    return false;
                }
            }
        }
        
        return true;
    }

}
