package com.signaltekno.composemultiselectlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.signaltekno.ListItem
import com.signaltekno.composemultiselectlazycolumn.ui.theme.ComposeMultiselectLazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var items by remember {
                mutableStateOf((1..20).map { item ->
                    ListItem(
                        title = "Item $item",
                        selected = false
                    )
                })
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items.size){ i ->
                    Row(
                      modifier = Modifier
                          .fillParentMaxWidth()
                          .clickable {
                              items = items.mapIndexed { j, item -> // iterate array
                                  if (i == j) { // if index is match change value of selected field
                                      item.copy(selected = !item.selected)
                                  } else item
                              }
                          }
                          .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(items[i].title)
                        if(items[i].selected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Color.Green,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}