package com.example.dictionary.view.main

import android.content.Context
import com.example.dictionary.R
import com.example.dictionary.model.Repository
import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel
import com.example.dictionary.presenter.DataPresenterRU
import com.example.dictionary.presenter.Interactor
import io.reactivex.rxjava3.core.Observable

class MainInteractor(
    private val context: Context,
    private val remoteRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return remoteRepository
            .getData(word)
            .map { dataModelFD ->
                val newList = dataModelFD.map {
                    var reorganizedDefinitions = ""
                    for (currentMeaning in it.meanings) {
                        reorganizedDefinitions =
                            "<p><i><small>(<span style = \"color: ${
                                context.getColor(R.color.color_text_subheader)
                            }\">" + currentMeaning.partOfSpeech + "</span>)</small></i>"
                        currentMeaning.definitions.forEachIndexed { index, definitions ->
                            reorganizedDefinitions += "<br><span style = \"color: ${
                                context.getColor(R.color.color_text_main)
                            }\"><b>${index + 1}.</b></span> " + definitions.definition
                            if (definitions.example != "")
                                reorganizedDefinitions += "<span style = \"color: ${
                                    context.getColor(R.color.teal_700)
                                }\"><i><b> Пример:</b> " + definitions.example + "</i></span>"
                        }
                        reorganizedDefinitions += "</p>"
                    }
                    DataPresenterRU(it.word, reorganizedDefinitions)
                }
                AppState.Success(newList)
            }
    }
}

