export type ElementType = string;
export type WeaponType  = string;


export type Character = {
  name?: string;
  rarity?: number;
  elementType?: ElementType;
  weaponType?:  WeaponType;
};

export type ByElementType = {
  elementType: ElementType;
  count: number;
  characters?: Character[];
};

export type StatCharacterResponse = {
  elementTypes: ElementType[];
  weaponTypes:  WeaponType[];
  byElementTypes: ByElementType[];
};

export type ElementI18n = {
  code: string;
  translations: Map<string, string>;
};

export type WeaponI18n = ElementI18n;
