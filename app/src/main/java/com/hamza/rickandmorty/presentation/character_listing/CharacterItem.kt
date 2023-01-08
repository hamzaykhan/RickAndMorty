package com.hamza.rickandmorty.presentation.character_listing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hamza.rickandmorty.domain.model.CharacterListing

@Composable
fun CharacterItem(
    character: CharacterListing,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(character.image)
                    .crossfade(true)
                    .build(),
                contentDescription = character.name + "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(
                        width = 60.dp,
                        height = 80.dp
                    )
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = character.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(
                    paddingValues = PaddingValues(horizontal = 8.dp)
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box (modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(character.statusColor())
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "${character.status} - ${character.species}",
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}