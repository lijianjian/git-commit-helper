package com.github.lijianjian.gitcommithelper.configuration

import com.intellij.openapi.options.Configurable
import com.intellij.ui.CollectionListModel
import javax.swing.JComponent

class PluginSettingsConfiguration : Configurable {

    private lateinit var pluginSettingsConfigurationPanel: PluginSettingsConfigurationPanel
    private val pluginSettingsState
        get() = PluginSettingsState.instance.state

    override fun createComponent(): JComponent? {
        pluginSettingsConfigurationPanel = PluginSettingsConfigurationPanel()
        return pluginSettingsConfigurationPanel.mainPanel
    }

    override fun isModified(): Boolean {
        return pluginSettingsConfigurationPanel.authorization.text != pluginSettingsState.jiraAuthorization
                || pluginSettingsConfigurationPanel.prefixesModel.items != pluginSettingsState.jiraProjectPrefixes
    }

    override fun apply() {
//        pluginSettingsState.messageWrapperType = pluginSettingsConfigurationPanel
//            .messageWrapperTypeDropdown
//            .selectedItem
//            .toString()
        pluginSettingsState.jiraAuthorization = pluginSettingsConfigurationPanel.authorization.text
        pluginSettingsState.jiraProjectPrefixes = pluginSettingsConfigurationPanel.prefixesModel.items
    }

    override fun getDisplayName(): String {
        return "JIRA Id Commit Message"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return pluginSettingsConfigurationPanel.getPreferredFocusedComponent()
    }

    override fun reset() {
//        pluginSettingsConfigurationPanel
//            .messageWrapperTypeDropdown
//            .selectedItem = pluginSettingsState.messageWrapperType
        pluginSettingsConfigurationPanel.prefixesModel = CollectionListModel(pluginSettingsState.jiraProjectPrefixes)
        pluginSettingsConfigurationPanel.prefixesList.model = pluginSettingsConfigurationPanel.prefixesModel
    }
}
