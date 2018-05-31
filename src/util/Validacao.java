package util;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;

public class Validacao {
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public static void isEmailValid(String email) throws Exception {
		if ((email == null) || (email.trim().length() == 0)) {
			throw new Exception("Por favor, preencha o campo e-mail com um e-mail válido!");
		}

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new Exception("Favor preencher o campo EMAIL com um EMAIL válido!");
		}
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static void isValidCNPJ(String cnpj) throws Exception {

		cnpj = cnpj.replace(".", ""); // removendo pontos
		cnpj = cnpj.replace("/", ""); // removendo barra
		cnpj = cnpj.replace("-", ""); // removendo hífen

		if ((cnpj == null) || (cnpj.length() != 14)) {
			throw new Exception("Informe um CNPJ válido!");
		}

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);

		if (!cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString())) {
			throw new Exception("Informe um CNPJ válido!");
		}
	}

	public static String getCpfCnpjFormatado(String cpfCnpj) {
		JFormattedTextField cnpj_cpf = new javax.swing.JFormattedTextField();

		String valor = cpfCnpj.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
		if (valor.length() == 11) {
			try {
				cnpj_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.MaskFormatter("###.###.###-##")));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				cnpj_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.MaskFormatter("##.###.###/####-##")));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}
		cnpj_cpf.setText(valor);

		return cnpj_cpf.getText();
	}
}
