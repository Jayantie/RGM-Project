package com.bludots.app.rgm.password.registration.views;

import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.services.EmailService;
import com.bludots.app.rgm.password.registration.services.RequestServices;
import com.bludots.app.rgm.password.registration.valueobjects.RequestVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

@Route(value = "")

@CssImport(value = "./styles/components/textfield.css")

public class MainView extends VerticalLayout {

	public MainView() {
		super();
		

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		
		EmailField email = new EmailField();
		add(email);
		email.setClassName("input");
//		email.setLabel("RGM REGISTRATION");

		email.setPlaceholder("Please insert your email");
		email.setWidth("300px");
		// email.setHelperText("Welcome to our registration page");
		email.setPattern(null);

		Button submitBtn = new Button("Submit");
		add(email, submitBtn);
		submitBtn.setWidth("300px");
		submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		submitBtn.setClassName("btn");
		submitBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
//				EmailService emailService = ContextProvider.getBean(EmailService.class);
//				emailService.sendmail(email.getValue(), "Token mail", "This is s test token mail");
				
				RequestServices requestServices =ContextProvider.getBean(RequestServices.class);
				RequestVO vo = new RequestVO();
				vo.setEmail(email.getValue());
				requestServices.persistRequest(vo);

						
				Notification.show("Please check your inbox for instructions.", 4000, Position.TOP_CENTER);

				email.clear();

			}
		});
		addClassName("form");
	}

}
