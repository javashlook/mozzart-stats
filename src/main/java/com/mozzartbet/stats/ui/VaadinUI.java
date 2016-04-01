package com.mozzartbet.stats.ui;

import static com.google.common.collect.Lists.*;

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

	private final TeamEditor teamEditor;
	private final Grid grid;
	private final TextField textFilter;
	private final Button btnNew;

	@Autowired
	public VaadinUI(TeamRepository repo, TeamEditor editor) {
		this.teamRepository = repo;
		this.teamEditor = editor;
		this.grid = new Grid();
		this.textFilter = new TextField();
		this.btnNew = new Button("New team", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(textFilter, btnNew);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, teamEditor);
		setContent(mainLayout);

		// Configure layouts and components
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "name", "mozzartName");

		textFilter.setInputPrompt("Filter by name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		textFilter.addTextChangeListener(e -> listTeams(e.getText()));

		// Connect selected Customer to editor or hide if none is selected
		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
				teamEditor.setVisible(false);
			}
				else {
					teamEditor.editTeam((Team) grid.getSelectedRow());
				}
			});

		btnNew.addClickListener(e -> teamEditor.editTeam(new Team("New", "NEW")));

		teamEditor.setChangeHandler(() -> {
			teamEditor.setVisible(false);
			listTeams(textFilter.getValue());
		});

		listTeams(null);
	}

	private void listTeams(String text) {
		if (StringUtils.isEmpty(text)) {
			grid.setContainerDataSource(
					new BeanItemContainer<Team>(Team.class, newArrayList(teamRepository.findAll())));
		}
		else {
			grid.setContainerDataSource(new BeanItemContainer<Team>(Team.class,
					teamRepository.findByNameStartsWithIgnoreCase(text)));
		}
	}

}