package com.vaultmessenger.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.vaultmessenger.ProfileImage
import com.vaultmessenger.model.Conversation
import com.vaultmessenger.modules.ReceiverUserRepository
import com.vaultmessenger.viewModel.ReceiverUserViewModel
import com.vaultmessenger.viewModel.ReceiverUserViewModelFactory

@Composable
fun ConversationItem(
    conversation: Conversation,
    navController: NavHostController,
    userId: String?,
) {
    val otherUserIdKey = if (conversation.userIds["userId1"] == userId) "userId2" else "userId1"
    val otherUserNameKey = if (conversation.userIds["userId1"] == userId) "userName2" else "userName1"
    val otherUserPhotoKey = if (conversation.userIds["userId1"] == userId) "profilePictureUrl_userId2" else "profilePictureUrl_userId1"

    val receiverUID = conversation.userIds[otherUserIdKey]

    // Access the receiver user's ViewModel directly
    val receiverUserRepository = ReceiverUserRepository(receiverUID)
    val receiverViewModelFactory = ReceiverUserViewModelFactory(receiverUserRepository)
    val receiverUserViewModel: ReceiverUserViewModel = viewModel(factory = receiverViewModelFactory)
    val receiverUser by receiverUserViewModel.receiverUser.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("Chat/${userId}/${receiverUID}")
            }
    ) {

        ProfileImage(
            userPhotoUrl = conversation.userPhotos[otherUserPhotoKey],
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = conversation.userNames[otherUserNameKey] ?: "Unknown",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = conversation.lastMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = conversation.timestamp, // Format this timestamp for better readability if needed
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}
