package org.nemwiz.jiracommitmessage.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vcs.CommitMessageI
import com.intellij.openapi.vcs.VcsDataKeys
import com.intellij.openapi.vcs.ui.Refreshable
import org.nemwiz.jiracommitmessage.services.JiraService
import org.nemwiz.jiracommitmessage.services.MyProjectService

class PluginAction : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val currentProject = actionEvent.project

        if (currentProject != null) {
            val projectService = actionEvent.project?.getService(MyProjectService::class.java)
            val newCommitMessage = projectService?.getTaskIdFromBranchName()
            val jiraService = actionEvent.project?.getService(JiraService::class.java)
            val summary = jiraService?.getSummary(newCommitMessage)
            getCommitPanel(actionEvent)?.setCommitMessage(summary)
        }
    }

    private fun getCommitPanel(actionEvent: AnActionEvent): CommitMessageI? {
        val data = Refreshable.PANEL_KEY.getData(actionEvent.dataContext)

        if (data is CommitMessageI) {
            return data
        }

        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(actionEvent.dataContext)
    }
}
