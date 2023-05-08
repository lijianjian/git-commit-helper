package com.github.lijianjian.gitcommithelper.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import git4idea.GitUtil
import java.util.*
import java.util.regex.Pattern

@Service(Service.Level.PROJECT)
class MyProjectService(private val project: Project) {

    fun getBranchName(): String? {
        val repositoryManager = GitUtil.getRepositoryManager(project)
        val branch = repositoryManager.repositories[0].currentBranch
        return matchBranchNameThroughRegex(branch?.name)
    }

    private fun matchBranchNameThroughRegex(valueToMatch: String?): String? {

        val pattern = Pattern.compile(String.format(Locale.US, "[LPD,lpd]+-[0-9]+", valueToMatch))
        val matcher = valueToMatch?.let { pattern.matcher(it) }
        if (matcher != null) {
            return if(matcher.find()) {
                return matcher.group();
            } else {
                null
            }
        }
        return null
    }


}
