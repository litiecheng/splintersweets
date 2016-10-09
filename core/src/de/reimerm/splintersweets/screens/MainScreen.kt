/*
 * Copyright (c) 2016. Marius Reimer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.reimerm.splintersweets.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import de.reimerm.splintersweets.abstract.AbstractStretchStage
import de.reimerm.splintersweets.actors.BackgroundActor
import de.reimerm.splintersweets.main.MainStage
import de.reimerm.splintersweets.utils.AssetsManager
import de.reimerm.splintersweets.utils.GameManager
import de.reimerm.splintersweets.utils.Resources

/**
 * Screen for the main game.
 *
 * Created by Marius Reimer on 10-Jun-16.
 */
class MainScreen : Screen {

    private var stage: Stage
    private var stretchStage: Stage

    constructor() {
        stage = MainStage()
        stretchStage = MainStretchStage()
    }

    override fun show() {
    }

    override fun pause() {
        GameManager.onPause()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun hide() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Start the render process
        stretchStage.viewport.apply()
        stretchStage.draw()

        stage.viewport.apply()
        stage.draw()
        stage.act(delta)
    }

    override fun resume() {
    }

    override fun dispose() {
        stage.dispose()
        stretchStage.dispose()
    }

    private inner class MainStretchStage : AbstractStretchStage {

        constructor() {
            addActor(BackgroundActor(AssetsManager.textureMap[Resources.RegionNames.BACKGROUND_NAME.name]))
        }
    }
}