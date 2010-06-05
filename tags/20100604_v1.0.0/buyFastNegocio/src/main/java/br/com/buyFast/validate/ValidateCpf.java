package br.com.buyFast.validate;


/**
 * Classe responsável pela validação de CPF.
 */
public class ValidateCpf {

	
	/**
	 * Verifica se o CPF passado pelo parâmetro é válido.<br />
	 * Exemplo CPF: <i>999.999.999-99</i> ou 99999999999.
	 * @param cpf - O CPF para verificação.
	 * @return <code>true</code> caso CPF válido. <code>falso</code> caso contrário.
	 */
	public boolean isCPFValid(String cpf) {
		if (cpf == null) {
			return false;
		}

		/* Substitui os caracteres do CPF 999.999.999-99*/
		if (cpf.length() != 11) {
			cpf = cpfNumber(cpf);
		}
		
		if (cpf.length() == 11) {
			cpf.trim();
			int num[] = new int[11];
			int dv1, dv2;
			int soma, resto;

			for (int i = 0; i < 11; i++) {
				num[i] = Integer.parseInt(cpf.substring(i, i + 1));
			}

			// calcula o primeiro dígito verificador
			soma = 10 * num[0] + 9 * num[1] + 8 * num[2] + 7 * num[3] + 6
					* num[4] + 5 * num[5] + 4 * num[6] + 3 * num[7] + 2
					* num[8];
			resto = soma % 11;
			dv1 = 11 - resto;
			if (dv1 > 9) {
				dv1 = 0;
			}

			// verifica se o primeiro dígito verificador está correto
			if (dv1 == num[9]) {
				// calcula o segundo dígito verificador
				soma = 11 * num[0] + 10 * num[1] + 9 * num[2] + 8 * num[3] + 7
						* num[4] + 6 * num[5] + 5 * num[6] + 4 * num[7] + 3
						* num[8] + 2 * num[9];

				resto = soma % 11;

				dv2 = 11 - resto;
				if (dv2 > 9) {
					dv2 = 0;
				}

				if (dv2 == num[10]) {
					return true;
				}

			}
		}
		
		return false;
	}
	
	/**
	 * Obter apenas números do CPF.
	 * @param s O CPF. Exemplo: 999.999.999-99 e retorna 99999999999.
	 * @return Os números do CPF.
	 */
	private String cpfNumber(String s) {
        if (s != null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return null;
    }
}
