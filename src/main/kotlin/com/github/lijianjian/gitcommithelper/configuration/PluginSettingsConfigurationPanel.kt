package com.github.lijianjian.gitcommithelper.configuration

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.CollectionListModel
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class PluginSettingsConfigurationPanel {

    var mainPanel: JPanel
    var messageWrapperTypeDropdown: ComboBox<String> = ComboBox()
    private var prefixes = PluginSettingsState.instance.state.jiraProjectPrefixes
    var prefixesList: JBList<String>
    var prefixesModel: CollectionListModel<String>
    private var toolbar: ToolbarDecorator
    private var prefixField: JBTextField

    init {


        prefixesModel = CollectionListModel<String>(prefixes)
        prefixesList = JBList(prefixesModel)
        prefixesList.setEmptyText("No prefixes configured")
        prefixField = JBTextField();

        toolbar = ToolbarDecorator.createDecorator(prefixesList).disableUpDownActions()


        mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("JIRA project prefixes"), prefixField, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPreferredFocusedComponent(): JComponent {
        return messageWrapperTypeDropdown
    }
}
