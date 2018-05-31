package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import basica.Cliente;
import bean.BeanPedido;
import util.Fachada;

@FacesConverter("converterCliente")
public class ConverterCliente implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				
				Fachada f = Fachada.getInstancia();

				return (Cliente) f.buscarClienteLogin(value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
//        if(string != null && string.trim().length() > 0) {
//            try {
//            	
//                BeanPedido beanPedido = (BeanPedido) fc.getExternalContext().getSessionMap().get("beanPedido");
//                return beanPedido.getCliente();
//                
//            } catch(NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
//            }
//        }
//        else {
//            return null;
//        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
