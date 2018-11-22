package org.wordpress.android.ui.stats.refresh.sections.insights

import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.CoroutineDispatcher
import org.wordpress.android.modules.UI_THREAD
import org.wordpress.android.ui.stats.refresh.sections.InsightsUiState
import org.wordpress.android.ui.stats.refresh.sections.InsightsUiState.StatsListState.DONE
import org.wordpress.android.ui.stats.refresh.sections.NavigationTarget
import org.wordpress.android.ui.stats.refresh.sections.StatsListViewModel
import org.wordpress.android.ui.stats.refresh.sections.insights.usecases.InsightsUseCase
import org.wordpress.android.util.map
import javax.inject.Inject
import javax.inject.Named

class InsightsListViewModel
@Inject constructor(
    @Named(UI_THREAD) mainDispatcher: CoroutineDispatcher,
    private val insightsUseCase: InsightsUseCase
) : StatsListViewModel(mainDispatcher) {
    private val _data = insightsUseCase.data.map { loadedData ->
        InsightsUiState(loadedData, DONE)
    }
    override val data: LiveData<InsightsUiState> = _data

    override val navigationTarget: LiveData<NavigationTarget> = insightsUseCase.navigationTarget

    override fun onCleared() {
        insightsUseCase.onCleared()
        super.onCleared()
    }
}
