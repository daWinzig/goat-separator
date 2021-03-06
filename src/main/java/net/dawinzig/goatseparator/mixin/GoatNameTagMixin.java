package net.dawinzig.goatseparator.mixin;

import net.dawinzig.goatseparator.GoatSeparator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(EntityRenderer.class)
public abstract class GoatNameTagMixin<T extends Entity> {

	@Shadow protected abstract void renderLabelIfPresent(
			T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

	@Inject(at = @At("HEAD"), method = "render", cancellable = true)
	public void render(
			T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
			int light, CallbackInfo ci) {
		if (GoatSeparator.keyBinding.isPressed()) {
			if (entity.getType() == EntityType.GOAT && ((GoatEntity)entity).isScreaming()) {
				double d = MinecraftClient.getInstance().gameRenderer.getCamera().getPos()
						.squaredDistanceTo(entity.getPos());
				if (d < 4096.0) {
					Text label;
					if (entity.hasCustomName()) {
						label = Text.literal("§6" + entity.getDisplayName().getString() + "§r");
					} else {
						label = Text.literal("§6" + Text.translatable("entity.goatseparator.screaming_goat").getString() + "§r");
					}
					this.renderLabelIfPresent(entity, label, matrices, vertexConsumers, light);
				}
				ci.cancel();
			}
		}
	}
}
