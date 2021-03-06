package nl.rubensten.texifyidea.structure.latex

import com.intellij.navigation.ItemPresentation
import nl.rubensten.texifyidea.TexifyIcons
import nl.rubensten.texifyidea.psi.LatexCommands

/**
 * @author Ruben Schellekens
 */
class LatexIncludePresentation(labelCommand: LatexCommands) : ItemPresentation {

    private val fileName: String

    init {
        if (labelCommand.commandToken.text != "\\include" &&
                labelCommand.commandToken.text != "\\includeonly" &&
                labelCommand.commandToken.text != "\\input" &&
                labelCommand.commandToken.text != "\\documentclass") {
            throw IllegalArgumentException("command is no \\include(only)-command")
        }

        // Get label name.
        val required = labelCommand.requiredParameters
        if (required.isEmpty()) {
            throw IllegalArgumentException("\\include(only) has no label name")
        }
        this.fileName = required[0]
    }

    override fun getPresentableText() = fileName

    override fun getLocationString() = ""

    override fun getIcon(b: Boolean) = TexifyIcons.DOT_INCLUDE!!
}
