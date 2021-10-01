package com.bludots.app.rgm.password.registration.views;

import java.util.Map;
import java.util.List;
import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.services.CredentialService;
import com.bludots.app.rgm.password.registration.services.RequestServices;
import com.bludots.app.rgm.password.registration.valueobjects.CredentialVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;


@Route(value = "PasswordFormView")
@CssImport(value = "./styles/components/textfield.css")
public class PasswordFormView extends VerticalLayout implements HasUrlParameter<String>{

	public PasswordFormView() {super();}
	
	@Override
	public void setParameter(BeforeEvent event,
	        @OptionalParameter String parameter) {

	    Location location = event.getLocation();
	    QueryParameters queryParameters = location.getQueryParameters();

	    Map<String, List<String>> parametersMap = queryParameters
	            .getParameters();
	    token = parametersMap.get("token").toString().substring(1, parametersMap.get("token").toString().length() - 1);
		RequestServices requestServices =ContextProvider.getBean(RequestServices.class);
		request = requestServices.findRequest(token);
		
		if(request != null) {
			password();
		}
		
		
	}
	
	private String token;
	private Request request;
	
	//method
	public void password () {
		

		addClassName("background2");

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		PasswordField password1 = new PasswordField("Password");
		password1.setPlaceholder("New password");
		password1.setWidth("300px");
		add(password1);

		PasswordField password2 = new PasswordField("Confirm password");
		password2.setPlaceholder("Confirm password");
		password2.setWidth("300px");
		password2.setHelperText("WELCOME on our registration page");
		add(password2);

		EmailField emailfield = new EmailField("Insert email");
		add(emailfield);
		

		emailfield.setLabel("RGM Registration");
		emailfield.setPlaceholder("Insert email");
		emailfield.setWidth("300px");
		emailfield.setPattern(null);
		emailfield.setValue(request.getEmail());

		Button submitbtn = new Button("Submit");
		add(emailfield, password1, password2, submitbtn);		
		submitbtn.setWidth("300px");

		submitbtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		submitbtn.addClassName("2btn");
		submitbtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				CredentialVO vo = new CredentialVO();
				vo.setEmail(emailfield.getValue());
				vo.setPassword(password1.getValue());
				vo.setConfirmPassword(password2.getValue());
				
				//compare password
				if (password1.equals(password2)){
				    Notification.show("Entered passwords does not match", 4000, Position.TOP_CENTER);
				    } else {
				
				CredentialService changePasswordService = ContextProvider
						.getBean(CredentialService.class);
				changePasswordService.persistPasswordChange(vo);

				Notification.show("Log In succesfull.", 4000, Position.TOP_CENTER);
				emailfield.clear();
				password1.clear();
				password2.clear();
				    }
			}
		});
		
	}

}
