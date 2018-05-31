package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import basica.Reserva;
import bean.BeanReserva;

@FacesConverter(forClass = Reserva.class, value = "converterReserva")
public class ConverterReserva implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {

		if (string == null)
			return null;

		BeanReserva bean = context.getApplication().evaluateExpressionGet(context, "#{beanReserva}", BeanReserva.class);

		Date data = null;    
       
        try {  
        	data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(string);
        } catch (ParseException ex) {  
            ex.printStackTrace();  
        }  
		
		for (Reserva reserva : bean.getReservas()) {
			if (reserva.getData().compareTo(data) == 0)
				return reserva.getIdReserva();
		}

		throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Reserva", string)));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof Reserva) ? ((Reserva) obj).toString() : null;
	}

}
