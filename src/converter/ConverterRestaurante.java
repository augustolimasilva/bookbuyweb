package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import basica.Restaurante;
import util.Fachada;

@FacesConverter("converterRestaurante")
public class ConverterRestaurante implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		if (string != null && string.trim().length() > 0) {
			try {
				Fachada f = Fachada.getInstancia();

				return (Restaurante) f.buscarRestauranteNome(string);
			} 
				
			 catch (Exception e) {
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
