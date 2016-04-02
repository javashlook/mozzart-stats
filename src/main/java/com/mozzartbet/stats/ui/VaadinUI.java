package com.mozzartbet.stats.ui;

import static com.google.common.collect.Lists.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mozzartbet.stats.domain.Team;
import com.mozzartbet.stats.repository.TeamRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private static final long serialVersionUID = -8529282718269235476L;

	private final TeamRepository teamRepository;

	private final TeamForm frmTeam;
	private final Grid grid;

	private final TextField txtFilter;
	private final Button btnNew;

	@Autowired
	public VaadinUI(TeamRepository teampRepository, TeamForm frmTeam) {
		this.teamRepository = teampRepository;
		this.frmTeam = frmTeam;
		this.grid = new Grid();
		this.txtFilter = new TextField();
		this.btnNew = new Button("New team", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout actions = new HorizontalLayout(txtFilter, btnNew);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, frmTeam);
		setContent(mainLayout);

		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "name", "mozzartName");

		txtFilter.setInputPrompt("Filter by name");

		txtFilter.addTextChangeListener(e -> listTeams(e.getText()));

		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
				frmTeam.setVisible(false);
			}
			else {
				frmTeam.editTeam((Team) grid.getSelectedRow());
			}
		});

		btnNew.addClickListener(e -> frmTeam.editTeam(new Team("New", "NEW")));

		frmTeam.setChangeHandler(() -> {
			frmTeam.setVisible(false);
			listTeams(txtFilter.getValue());
		});

		listTeams(null);
	}

	private void listTeams(String text) {
		final List<Team> teams;
		if (StringUtils.isEmpty(text)) {
			teams = newArrayList(teamRepository.findAll());
		}
		else {
			teams = teamRepository.findByNameStartsWithIgnoreCase(text);
		}

		grid.setContainerDataSource(new BeanItemContainer<Team>(Team.class, teams));
	}

}