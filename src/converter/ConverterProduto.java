package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import basica.Produto;
import basica.Restaurante;
import util.Fachada;
import util.UtilSession;

@FacesConverter("converterProduto")
public class ConverterProduto implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string != null && string.trim().length() > 0) {
			try {
				Restaurante restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
				Fachada f = Fachada.getInstancia();

				return (Produto) f.buscarProdutoDescricao(string, restaurante.getIdRestaurante());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
