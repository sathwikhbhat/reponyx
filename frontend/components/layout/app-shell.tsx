import { cn } from "@/lib/utils";
import { ReponyxIcon } from "@/components/icons/reponyx-icon";

export function BrandMark({className}: { className?: string }) {
    return (
        <div
            className={cn(
                "flex items-center gap-2.5 font-semibold tracking-tight",
                className
            )}
        >
            <ReponyxIcon className="size-8 rounded-[10px]"/>
            <span className="font-heading text-[1.05rem] leading-none">Reponyx</span>
        </div>
    );
}
