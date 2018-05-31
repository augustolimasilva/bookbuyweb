package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import basica.TipoRestaurante;
import bean.BeanRestaurante;

@FacesConverter(forClass = TipoRestaurante.class, value = "converterTipo")
public class ConverterTipo implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		// TODO Auto-generated method stub
		if (string == null)
			return null;

		BeanRestaurante data = context.getApplication().evaluateExpressionGet(context, "#{beanRestaurante}",
				BeanRestaurante.class);

		for (TipoRestaurante tipoRestaurante : data.getTipos()) {
			if (tipoRestaurante.getDescricao().equals(string))
				return tipoRestaurante;
		}

		throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to TipoRestaurante", string)));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof TipoRestaurante) ? ((TipoRestaurante) obj).getDescricao() : null;
	}

}
