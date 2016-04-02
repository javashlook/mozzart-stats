package com.mozzartbet.stats.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.mozzartbet.stats.domain.Team;
import com.mozzartbet.stats.repository.TeamRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * See e.g. AbstractForm in Virin (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
public class TeamForm extends VerticalLayout {

    private static final long serialVersionUID = -6755972697495293115L;

    private final TeamRepository teamRepository;

    private Team team;

    @PropertyId("name")
    final TextField txtName = new TextField("Name");
    @PropertyId("mozzartName")
    final TextField txtMozzartName = new TextField("Mozzart name");

    /* Action buttons */
    final Button btnSave = new Button("Save", FontAwesome.SAVE);
    final Button btnCancel = new Button("Cancel");
    final Button btnDelete = new Button("Delete", FontAwesome.TRASH_O);
    final CssLayout cssActions = new CssLayout(btnSave, btnCancel, btnDelete);

    @Autowired
    public TeamForm(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;

        addComponents(txtName, txtMozzartName, cssActions);

        setSpacing(true);

        cssActions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        btnSave.addClickListener(e -> teamRepository.save(team));
        btnDelete.addClickListener(e -> teamRepository.delete(team));
        btnCancel.addClickListener(e -> editTeam(team));

        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editTeam(Team team) {
        final boolean persisted = team.getId() != null;

        if (persisted) {
            this.team = teamRepository.findOne(team.getId());
        }
        else {
            this.team = team;
        }

        btnCancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(this.team, this);

        setVisible(true);

        // A hack to ensure the whole form is visible
        btnSave.focus();
        txtName.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        btnSave.addClickListener(e -> h.onChange());
        btnDelete.addClickListener(e -> h.onChange());
    }

}
